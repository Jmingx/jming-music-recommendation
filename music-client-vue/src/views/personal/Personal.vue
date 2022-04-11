<template>
  <div class="personal">
    <div class="personal-info">
      <div class="personal-img">
        <img :src="userPic ? attachImageUrl(userPic) : userPic" alt="" @click="dialogTableVisible = true"/>
      </div>
      <div class="personal-msg">
        <div class="username">用户名：{{ personalInfo.username }}</div>
        <div class="introduction">介绍：{{ personalInfo.introduction }}</div>
      </div>
      <el-button class="edit-info" round :icon="edit" @click="goPage()">修改个人信息</el-button>
    </div>
    <div class="personal-body">
      <div class="shoucang" align="center">我的收藏</div>
      <song-list :songList="collectSongList">
<!--        <template v-slot:title>我的收藏</template>-->
      </song-list>
    </div>
    <el-dialog v-model="dialogTableVisible" title="修改头像">
      <upload></upload>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import {
  defineComponent,
  nextTick,
  ref,
  markRaw,
  computed,
  watch,
  reactive,
} from "vue";
import { useStore } from "vuex";
import { Edit } from "@element-plus/icons-vue";
import SongList from "@/components/SongList.vue";
import Upload from "./Upload.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { PERSONAL_DATA } from "@/enums";

export default defineComponent({
  components: {
    SongList,
    Upload,
  },
  setup() {
    const store = useStore();

    const { routerManager, attachImageUrl } = mixin();

    const edit = markRaw(Edit);
    const dialogTableVisible = ref(false);
    const collectSongList = ref([]); // 收藏的歌曲
    const personalInfo = reactive({
      username: "",
      userSex: "",
      birth: "",
      location: "",
      introduction: "",
    });
    const userId = computed(() => store.getters.userId);
    const userPic = computed(() => store.getters.userPic);
    watch(userPic, () => {
      dialogTableVisible.value = false;
    });

    function goPage() {
      routerManager(PERSONAL_DATA, { path: PERSONAL_DATA });
    }
    function getUserInfo(id) {
      HttpManager.getUserOfId(id)
        .then((res) => {
          personalInfo.username = res[0].consumerUsername;
          personalInfo.userSex = res[0].consumerSex;
          personalInfo.birth = res[0].consumerBirth;
          personalInfo.introduction = res[0].consumerIntroduction;
          personalInfo.location = res[0].consumerLocation;
        })
        .catch((err) => {
          console.error(err);
        });
    }
    // 收藏的歌曲ID
    async function getCollection(userId) {
      console.log("userId",userId)
      const result = (await HttpManager.getCollectionOfUser(userId)) as any[];
      console.log("result",result)
      const collectIDList = result || []; // 存放收藏的歌曲ID
      // 通过歌曲ID获取歌曲信息
      for (const item of collectIDList) {
        getCollectSongList(item.musicId);
      }
    }
    // 获取收藏的歌曲
    function getCollectSongList(id) {
      HttpManager.getSongOfId(id)
        .then((res) => {
          collectSongList.value.push(res[0]);
        })
        .catch((err) => {
          console.error(err);
        });
    }

    nextTick(() => {
      getUserInfo(userId.value);
      getCollection(userId.value);
    });

    return {
      edit,
      userPic,
      dialogTableVisible,
      collectSongList,
      personalInfo,
      attachImageUrl,
      goPage,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/personal.scss";

.shoucang{
  font-family:Arial,Helvetica,sans-serif;
  font-size:1em;
  vertical-align:middle;
  font-weight:normal;
  font-size: 20px;
  font-weight: 600;
}
</style>
