<template>
  <div class="song-container">
    <div class="song-pic">
      <img :src="songPic"/>
    </div>
    <ul class="song-info">
      <li>歌手：{{ singerName }}</li>
      <li>歌曲：{{ songTitle }}</li>
    </ul>
  </div>
  <div class="container">
    <div class="lyric-container">
      <el-container>
        <el-header>
          <div class="grid-content bg-purple-dark">
            <div class="block" align="center">
              <span class="demonstration">评分：</span>
              <el-rate
                  v-model=curScore
                  :max=10
                  :colors="colors"
                  show-score="true"
                  @click="change"
                  :disabled="disable"
              >
              </el-rate>
            </div>
          </div>
        </el-header>
        <hr>
        <el-main>
          <div class="song-lyric">
            <transition-group name="lyric-fade">
              <!--有歌词-->
              <ul
                  :style="{ top: lrcTop }"
                  class="has-lyric"
                  v-if="lyricArr.length"
                  key="has-lyric"
              >
                <li v-for="(item, index) in lyricArr" :key="index">
                  {{ item[1] }}
                </li>
              </ul>
              <!--没歌词-->
              <div v-else class="no-lyric" key="no-lyric">
                <span>暂无歌词</span>
              </div>
            </transition-group>
          </div>

        </el-main>
      </el-container>

      <hr>

      <comment :playId="songId" :type="0"></comment>
    </div>
  </div>
</template>

<script lang="ts">
import {computed, defineComponent, getCurrentInstance, ref, watch} from 'vue';
import {useStore} from "vuex";
import Comment from "@/components/Comment.vue";
import {parseLyric} from "@/utils";
import {HttpManager} from "@/api";

interface resContent {
  code: number;
  msg: string;
  data: object;
}

export default defineComponent({
  components: {
    Comment,
  },
  data() {
    return {
      // curScore: 0,
      disable: false,
    }
  },
  setup() {
    const store = useStore();

    const lrcTop = ref("80px"); // 歌词滑动
    const lyricArr = ref([]); // 当前歌曲的歌词
    const songId = computed(() => store.getters.songId); // 歌曲ID
    const lyric = computed(() => store.getters.lyric); // 歌词
    const currentPlayList = computed(() => store.getters.currentPlayList); // 存放的音乐
    const currentPlayIndex = computed(() => store.getters.currentPlayIndex); // 当前歌曲在歌曲列表的位置
    const curTime = computed(() => store.getters.curTime);
    const songTitle = computed(() => store.getters.songTitle); // 歌名
    const singerName = computed(() => store.getters.singerName); // 歌手名
    const songPic = computed(() => store.getters.songPic); // 歌曲图片
    const userId = computed(() => store.getters.userId);
    const score = computed(() => store.getters.score);
    const curScore = ref(0);

    watch(songId, () => {
      console.log("watch cur", currentPlayList.value[currentPlayIndex.value])
      lyricArr.value = parseLyric(currentPlayList.value[currentPlayIndex.value].musicLyric);
    })

    watch(score,()=>{
      curScore.value = score.value
    })

    // 处理歌词位置及颜色
    watch(curTime, () => {
      if (lyricArr.value.length !== 0) {
        for (let i = 0; i < lyricArr.value.length; i++) {
          if (curTime.value >= lyricArr.value[i][0]) {
            for (let j = 0; j < lyricArr.value.length; j++) {
              (document.querySelectorAll(".has-lyric li") as NodeListOf<HTMLElement>)[j].style.color = "#000";
              (document.querySelectorAll(".has-lyric li") as NodeListOf<HTMLElement>)[j].style.fontSize = "14px";
            }
            if (i >= 0) {
              lrcTop.value = -i * 30 + 50 + "px";
              (document.querySelectorAll(".has-lyric li") as NodeListOf<HTMLElement>)[i].style.color = "#95d2f6";
              (document.querySelectorAll(".has-lyric li") as NodeListOf<HTMLElement>)[i].style.fontSize = "18px";
            }
          }
        }
      }
    })

    lyricArr.value = lyric.value ? parseLyric(lyric.value) : [];

    // function change() {
    //   if (curScore.value == 0){
    //     return
    //   }
    //   // const { proxy } = getCurrentInstance();
    //   console.log("change", curScore.value)
    //   //上传分数
    //   const params = new URLSearchParams();
    //   params.append("musicId", songId.value);
    //   params.append("consumerId", userId.value);
    //   console.log("score", curScore);
    //   params.append("score", curScore.value+"");
    //   console.log("consumerId", params)
    //   HttpManager.setMusicRank(params).then((data) => {
    //         let res = JSON.parse(JSON.stringify(data));
    //         if (res && res.code == 0) {
    //             console.log("ok", curScore)
    //           commit("setScore", score.value);
    //           computed(() => store.getters.score);
    //         } else if (res.code == 1) {
    //           console.log("wrong", res.msg)
    //           curScore.value = score.value
    //         } else {
    //           console.log("error", res.msg)
    //           curScore.value = score.value
    //         }
    //       }
    //   )
    // }

    return {
      songPic,
      singerName,
      songTitle,
      lrcTop,
      lyricArr,
      songId,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      userId,
      score,
      curScore,
      // change
    }
  },
  methods: {
    change() {
      if (this.curScore == 0){
        return
      }
      // const { proxy } = getCurrentInstance();
      console.log("change", this.curScore)
      //上传分数
      const params = new URLSearchParams();
      params.append("musicId", this.songId);
      params.append("consumerId", this.userId);
      console.log("score", this.curScore);
      params.append("score", this.curScore.toString());
      console.log("consumerId", params)
      HttpManager.setMusicRank(params).then((data) => {
            let res = JSON.parse(JSON.stringify(data));
            if (res && res.code == 0) {
                console.log("ok", this.curScore)
              // 修改store里的值
              this.$store.commit("setScore",this.curScore)
            } else if (res.code == 1) {
              console.log("wrong", res.msg)
              this.curScore = this.score
            } else {
              console.log("error", res.msg)
              this.curScore = this.score
            }
          }
      )
    },

    // init() {
    //   //加载初始化分数，(music_id,user_id)作为唯一索引，如果不存在，则把this.disable=true
    //   HttpManager.getRankOfMusicId(this.songId).then((data) => {
    //     let res = JSON.parse(JSON.stringify(data));
    //     if (res && res.code == 0) {
    //       this.score = res.score;
    //       if (this.score != 0) {
    //         // this.disable = true;
    //         console.log("ok", this.score)
    //       }
    //     } else {
    //       console.log("error", res.msg)
    //     }
    //   })
    // }
  },
  created() {
    // this.init();
  }
});
</script>

<style lang="scss" scored>
@import "@/assets/css/var.scss";

.container {
  padding-top: $header-height + 30px;
}

.song-container {
  position: fixed;
  top: 120px;
  left: 50px;

  .song-pic {
    height: 300px;
    width: 300px;
    overflow: hidden;
    border: 4px solid white;
    border-radius: 12px;

    img {
      height: 100%;
    }
  }

  .song-info {
    width: 300px;

    li {
      width: 100%;
      line-height: 40px;
      font-size: 18px;
      text-align: center;
    }
  }
}

.lyric-container {
  margin: 0 150px 0px 400px;
  border-radius: 12px;
  padding: 80px 20px 30px 20px;
  font-family: $font-family;
  background-color: $color-white;

  .song-lyric {
    position: relative;
    min-height: 300px;
    padding: 30px 0;
    overflow: auto;
    // text-align: center;
    .has-lyric {
      position: absolute;
      transition: all 1s;

      li {
        width: 100%;
        height: 40px;
        text-align: center;
        font-size: 14px;
        line-height: 40px;
      }
    }

    .no-lyric {
      position: absolute;
      margin: 100px 0;

      span {
        font-size: 18px;
        text-align: center;
      }
    }
  }
}

.lyric-fade-enter,
.lyric-fade-leave-to {
  transform: translateX(30px);
  opacity: 0;
}

.lyric-fade-enter-active,
.lyric-fade-leave-active {
  transition: all 0.3s ease;
}
</style>
