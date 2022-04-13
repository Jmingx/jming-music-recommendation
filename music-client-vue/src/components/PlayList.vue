<template>
  <div class="play-list">
    <div class="section-title" v-if="title">{{ title }}</div>
    <ul class="section-content">
      <li class="content-item" v-for="(item, index) in playList" :key="index">
        <div class="kuo">
          <img class="item-img" :src="attachImageUrl(item.imgAddress)" alt=""/>
          <div class="mask" @click="goAblum(item)">
            <yin-icon :icon="BOFANG"></yin-icon>
          </div>
        </div>
        <p class="item-name">{{ item.singerName || item.musicName }}</p>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import {computed, defineComponent, getCurrentInstance, ref, toRefs} from "vue";
import YinIcon from "@/components/layouts/YinIcon.vue";
import mixin from "@/mixins/mixin";
import {ICON, LYRIC} from "@/enums";
import {HttpManager} from "@/api";

export default defineComponent({
  components: {
    YinIcon,
  },
  props: {
    title: String,
    playList: Array,
    path: String,
  },
  setup(props) {
    const {proxy} = getCurrentInstance();
    const {routerManager, attachImageUrl} = mixin();

    const {path} = toRefs(props);
    const BOFANG = ref(ICON.BOFANG);

    // ==================================
    const {getSongTitle, getSingerName, playMusic} = mixin();
    const {playList} = toRefs(props);

    function handleClick(row) {
      playMusic({
        id: row.musicId,
        url: row.musicAddress,
        pic: row.imgAddress,
        index: row.index,
        name: row.musicName,
        lyric: row.musicLyric,
        currentSongList: playList.value,
      });
    }

    // ==================================
    function goAblum(item) {
      // proxy.$store.commit("setSongDetails", item);
      console.log("item", item.musicId || item.singerId, item);
      // 点击歌曲，播放并且把推荐列表放入当前列表
      if (item.musicId) {
        handleClick(item);
      } else {
        routerManager(path.value, {path: `/${path.value}/${item.singerId}`});
      }
    }

    return {
      BOFANG,
      goAblum,
      attachImageUrl,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/play-list.scss";
</style>
