package club.jming.musicSpider.utils;

import java.io.File;

public class Constant {

    public static final String PROJECT_PATH = System.getProperty("user.dir") + "/source";;

    /* 歌曲图片，歌手图片，歌曲文件，歌单图片等文件的存放路径 */
    /**
     * 歌手封面
     */
    public static String AVATOR_IMAGES_PATH =  "/img/avatorImages/";
    public static String SONGLIST_PIC_PATH =  "/img/songListPic/";
    /**
     * 歌曲封面
     */
    public static String SONG_PIC_PATH =  "/img/songPic/";
    /**
     * 歌曲地址
     */
    public static String SONG_PATH =  "/song/";
    /**
     * 歌手封面
     */
    public static String SINGER_PIC_PATH =  "/img/singerPic/";
    /**
     * 专辑封面
     */
    public static String ALBUM_PIC_PATH =  "/img/albumPic/";


    public static String baseUrl = "https://www.kugou.com/yy/special/single/3823475.html";
    public static String albumUrl = "https://www.kugou.com/album/";
    public static String singerUrl = "https://www.kugou.com/singer/";
//    public static String downloadMusicSrc = "D:/tmp/music/source/";
//    public static String downloadImgSrc = "D:/tmp/music/img/";
//    public static String downloadAlbumImgSrc = "D:/tmp/music/album/";
//    public static String downloadSingerImgSrc = "D:/tmp/music/singer/";
}
