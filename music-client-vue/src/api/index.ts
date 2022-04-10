import {get, post} from './request'

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

    // =======================> 歌手 API
    // 返回所有歌手
    getAllSinger: () => get('singer'),
    // 通过性别对歌手分类
    getSingerOfSex: (sex) => get(`singer/sex/detail?sex=${sex}`),

    // =======================> 收藏 API
    // 返回的指定用户ID的收藏列表
    getCollectionOfUser: (userId) => get(`collection/detail?userId=${userId}`),
    // 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
    setCollection: (params) => post(`collection/add`, params),

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

    // ========================用户评分 API
    // 提交评分
    setMusicRank: (params) => post(`rankMusic/add`, params),
    // 获取指定歌曲的评分
    getRankOfMusicId: (musicId) => get(`rankMusic?musicId=${musicId}`),
    // 获取指定用户的歌单评分
    getUserRankByConsumerId: (consumerId, musicId) => get(`/rankMusic/user?consumerId=${consumerId}&musicListId=${musicId}`),

}

export {HttpManager}
