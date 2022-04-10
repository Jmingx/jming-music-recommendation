package club.jming.musicServer.constant;

public class Constants {

    public static final String PROJECT_PATH = "file:" + System.getProperty("user.dir") + "/source";;

    /* 歌曲图片，歌手图片，歌曲文件，歌单图片等文件的存放路径 */
    /**
     * 歌手封面
     */
    public static String AVATOR_IMAGES_PATH = PROJECT_PATH + "/img/avatorImages/";
    public static String SONGLIST_PIC_PATH = PROJECT_PATH + "/img/songListPic/";
    /**
     * 歌曲封面
     */
    public static String SONG_PIC_PATH = PROJECT_PATH + "/img/songPic/";
    /**
     * 歌曲地址
     */
    public static String SONG_PATH = PROJECT_PATH + "/song/";
    /**
     * 歌手封面
     */
    public static String SINGER_PIC_PATH = PROJECT_PATH + "/img/singerPic/";
    /**
     * 专辑封面
     */
    public static String ALBUM_PIC_PATH = PROJECT_PATH + "/img/albumPic/";
}
