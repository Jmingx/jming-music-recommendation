import {get, post} from './request'
import {useStore} from "vuex";

const HttpManager = {
    // =======================> 用户 API
    // 登录
    signIn: (params) => post(`user/login/status`, params),
    // 注册
    SignUp: (params) => post(`user/add`, params),
    // 更新用户信息
    updateUserMsg: (params) => post(`user/update`, params),
    // 返回指定ID的用户
    getUserOfId: (id) => get(`user/detail?id=${id}`),

    // =======================> 歌单 API
    // 获取全部歌单
    getSongList: () => get('musicList'),
    // 获取歌单类型
    getSongListOfStyle: (style) => get(`musicList/style/detail?style=${style}`),
    // 返回标题包含文字的歌单
    getSongListOfLikeTitle: (keywords) => get(`musicList/likeTitle/detail?title=${keywords}`),
    // 返回歌单里指定歌单ID的歌曲
    getListSongOfSongId: (musicListId) => get(`listSong/detail?musicListId=${musicListId}`),
    // 播放歌曲得分
    playMusic: (userId,musicId) => get(`music/play?userId=${userId}&musicId=${musicId}`),

    // =======================> 歌手 API
    // 返回所有歌手
    getAllSinger: () => get('singer'),
    // 通过性别对歌手分类
    getSingerOfSex: (sex) => get(`singer/sex/detail?sex=${sex}`),
    //通过歌曲Id获取歌手名
    getSingerByMusicId:(musicId) => get(`singer/find?musicId=${musicId}`),

    // =======================> 收藏 API
    // 返回的指定用户ID的收藏列表
    getCollectionOfUser: (userId) => get(`collection/detail?userId=${userId}`),
    // 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
    setCollection: (params) => post(`collection/add`, params),
    // 通过userId musicId获取收藏
    getCollectionByUserIdAndMusicId:(userId,musicId) => get(`collection/collected?userId=${userId}&musicId=${musicId}`),
    //取消收藏
    deleteCollectByUserIdAndMusicId:(userId,musicId) => get(`collection/delete?userId=${userId}&musicId=${musicId}`),

    // =======================> 评分 API
    // 提交评分
    setRank: (params) => post(`rankList/add`, params),
    // 获取指定歌单的评分
    getRankOfSongListId: (musicListId) => get(`rankList?musicListId=${musicListId}`),
    // 获取指定用户的歌单评分
    getUserRank: (consumerId, musicListId) => get(`/rankList/user?consumerId=${consumerId}&musicListId=${musicListId}`),

    // =======================> 评论 API
    // 添加评论
    setComment: (params) => post(`comment/add`, params),
    // 点赞
    setSupport: (params) => post(`comment/like`, params),
    // 返回所有评论
    getAllComment: (type, id) => {
        let url = ''
        if (type === 1) {
            url = `comment/musicList/detail?musicListId=${id}`
        } else if (type === 0) {
            url = `comment/music/detail?musicId=${id}`
        }
        return get(url)
    },

    // =======================> 歌曲 API
    // 返回指定歌曲ID的歌曲
    getSongOfId: (id) => get(`music/detail?id=${id}`),
    // 返回指定歌手ID的歌曲
    getSongOfSingerId: (id) => get(`music/singer/detail?singerId=${id}`),
    // 返回指定歌手名的歌曲
    getSongOfSingerName: (keywords) => get(`music/singerName/detail?name=${keywords}`),
    // 下载音乐
    downloadMusic: (url) => get(url, {responseType: 'blob'}),
    // 返回所有歌曲
    getMusic:()=>get(`music`),

    // ========================>用户评分 API
    // 提交评分
    setMusicRank: (params) => post(`rankMusic/add`, params),
    // 获取指定歌曲的评分
    getRankOfMusicId: (musicId) => get(`rankMusic?musicId=${musicId}`),
    // 获取指定用户的歌单评分
    getUserRankByConsumerId: (consumerId, musicId) => get(`/rankMusic/user?consumerId=${consumerId}&musicListId=${musicId}`),

    // ========================>推荐系统 API
    // 获取推荐列表
    getRecommendationList:(topK)=>get(`recommendation?topK=${topK}`)
}

export {HttpManager}
