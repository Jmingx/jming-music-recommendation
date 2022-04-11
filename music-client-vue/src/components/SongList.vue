<template>
  <div class="content">
    <el-table
        style="width: 100%"
        stripe
        highlight-current-row
        :data="dataList"
        @row-click="handleClick">
      <el-table-column type="index" prop="index" label="排序" width="100"/>
      <el-table-column prop="songName" label="歌曲名"/>
      <el-table-column prop="singerName" label="歌手"/>
      <!--      <el-table-column prop="album" label="专辑" />-->
    </el-table>
  </div>
</template>

<script lang="ts">
import {defineComponent, toRefs, computed} from "vue";
import mixin from "@/mixins/mixin";
import {HttpManager} from "@/api";

export default defineComponent({
  props: {
    songList: Array,
  },
  setup(props) {
    const {getSongTitle, getSingerName, playMusic} = mixin();

    const {songList} = toRefs(props);
    const dataList = computed(() => {
      const list = [];
      songList.value.forEach((item: any, index) => {
        // console.log("item",item);
        item["index"] = index;
        item["songName"] = getSongTitle(item.musicName);
        //查询获取歌手名、专辑名
        HttpManager.getSingerByMusicId(item.musicId).then((data) => {
          let res = JSON.parse(JSON.stringify(data));
          // console.log("data",res)
          if (res && res.code == 0) {
            item["singerName"] = res.data.singerName
            // console.log("ok",res.msg)
          } else {
            console.log("error", res.msg)
          }
        });
        // item["singerName"] = getSingerName(item.name);
        // item["songName"] = JSON.parse(JSON.stringify(item)).musicName
        // item["singerName"] = getSingerName(item.name);
        // item["index"] = index;
        // list.push({
        //   "songName":JSON.parse(JSON.stringify(item)).musicName,
        //   "index":index
        // });
        list.push(item);
      });
      // console.log("list",list);
      return list;
    });

    function handleClick(row) {
      playMusic({
        id: row.musicId,
        url: row.musicAddress,
        pic: row.imgAddress,
        index: row.index,
        name: row.musicName,
        lyric: row.musicLyric,
        currentSongList: songList.value,
      });
    }

    return {
      dataList,
      handleClick,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.content {
  background-color: $color-white;
  border-radius: $border-radius-songlist;
  padding: 3%;
}

.content:deep(.el-table__row.current-row) {
  color: $color-blue-active;
  font-weight: bold;
}

.content:deep(.el-table__row) {
  cursor: pointer;
}
</style>
