package club.jming.musicSpider.component;

import club.jming.musicSpider.domain.BasicAlbum;
import club.jming.musicSpider.domain.BasicMusic;
import club.jming.musicSpider.domain.BasicSinger;
import club.jming.musicSpider.dto.KugouMusicDTO;
import club.jming.musicSpider.service.SpiderService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ember
 */
@Component
@Slf4j
public class SpiderKugou {

    @Autowired
    private SpiderService spiderService;

    /**
     * 1.歌曲的hash值 [正则匹配]
     * 2.歌曲的album_id [正则匹配]
     * 3.歌名 [正则匹配]
     * 4.歌曲下载地址 [正则匹配]
     * 5.unicode转中文 [正则匹配]
     */
    private static final String hashRegex = ",\"hash\":\"(.*?)\",\"brief\":";
    private static final String albumIdRegex = "\"album_id\":(.*?),\"hash\"";
    private static final String unicodeRegex = "(\\\\u(\\p{XDigit}{4}))";

    private static String baseUrl = "https://www.kugou.com/yy/special/single/3823475.html";
    private static String albumUrl = "https://www.kugou.com/album/";
    private static String singerUrl = "https://www.kugou.com/singer/";
    private static String downloadMusicSrc = "D:/tmp/music/source/";
    private static String downloadImgSrc = "D:/tmp/music/img/";
    private static String downloadAlbumImgSrc = "D:/tmp/music/album/";
    private static String downloadSingerImgSrc = "D:/tmp/music/singer/";

    public List<KugouMusicDTO> parameter() {
        return this.parameter(baseUrl);
    }

    /**
     * 爬取歌单的每一首歌曲
     *
     * @param baseUrl 酷狗歌单链接地址
     * @throws Exception
     * @return
     */
    public List<KugouMusicDTO> parameter(String baseUrl) {
        //JSON工具
        ObjectMapper mapper = new ObjectMapper();
        //获取歌单的Document
        Document musicList = null;
        try {
            musicList = Jsoup.connect(baseUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //正则表达式匹配出歌单所有hash和album_id对应的值
        List<String> musicHash = myRegexpList(musicList.toString(), hashRegex);
        List<String> musicId = myRegexpList(musicList.toString(), albumIdRegex);

        List<KugouMusicDTO> kugouMusicDTOList = new LinkedList<>();

        for (int i = 0; i < musicId.size(); i++) {
            log.info("爬取第 [{}] 首歌",i);
            //调出歌单每首歌的接口
            String mp3 = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&callback=jQuery191043744424319789976_1628418579111" +
                    "&hash=" + musicHash.get(i) + "&dfid=08yAMj3R2Rtq2vHAnv0NSCKV&mid=94bf13b027c6ce8289bdfa422f5e783e&platid=4" +
                    "&album_id=" + musicId.get(i) + "&_=1628418579112";
            //获取歌曲的doc
            Document musicPlayer = parsUrl(mp3);
            //获取歌曲的歌名
            //将接口的数据转为JSON对象
            String mp3Doc = null;
            try {
                mp3Doc = mapper.writeValueAsString(musicPlayer.body().text());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //转JSON有反斜杠，将反斜杠替换掉即可
            mp3Doc = unicodeToString(mp3Doc);
            //把json中的单斜杠替换掉
            mp3Doc = mp3Doc.replaceAll("\\\\","");
            mp3Doc = mp3Doc.substring(mp3Doc.indexOf("(")+1,mp3Doc.lastIndexOf(")"));
//            mp3Doc = unicodeToString(mp3Doc);
            //可能会抛出异常
            JSONObject jsonObject = jsonObject = JSON.parseObject(mp3Doc);

            JSONObject dataJson = null;
            //数组形式暂时不处理
            if (jsonObject.get("data") instanceof JSONArray){
                log.error("数组形式暂时不处理");
                continue;
            }else {
                dataJson = (JSONObject) jsonObject.get("data");
                String imgUrl = (String) dataJson.get("img");
                String songUrl = (String) dataJson.get("play_url");
                if (StringUtils.isEmpty(imgUrl) || StringUtils.isEmpty(songUrl)){
                    continue;
                }

                String albumName = (String) dataJson.get("album_name");
                String songName = (String) dataJson.get("song_name");
                List<BasicSinger> singers = new LinkedList<>();
                log.info("============封面地址==============:{}" + imgUrl);
                log.info("============歌曲地址==============" + songUrl);
                log.info("============专辑名称==============" + albumName);
                log.info("============歌曲名称==============" + songName);
                JSONArray authors = (JSONArray) dataJson.get("authors");

                KugouMusicDTO kugouMusicDTO = new KugouMusicDTO();
                kugouMusicDTO.setSingers(new LinkedList<>());

                for (Object author : authors) {
                    BasicSinger singer = new BasicSinger();
                    JSONObject jsonAuthor = (JSONObject) author;
                    String singerName = (String) jsonAuthor.get("author_name");
                    String singerImgUrl = (String) jsonAuthor.get("avatar");
                    String singerId = String.valueOf(jsonAuthor.get("author_id"));

                    singer.setUpdateTime(new Date());

                    try {
                        Document singerDocument = Jsoup.connect(singerUrl + singerId + ".html").get();
                        Elements singerDescriptionElement = singerDocument.select("body > div.wrap.clear_fix > div.sng_ins_1 > div.top > div > p");
                        singer.setSingerDescription(singerDescriptionElement.html());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String singerImgAddress = downLoad(singerImgUrl,singerName,downloadSingerImgSrc+singerName+".jpg");
                    singer.setImgAddress(singerImgAddress);
                    singer.setSingerName(singerName);
                    log.info("============歌手名称==============" + singerName);
                    log.info("============歌手封面==============" + singerImgAddress);
                    kugouMusicDTO.getSingers().add(singer);
                }

                //音乐信息
                BasicMusic basicMusic = new BasicMusic();

                basicMusic.setMusicName(songName);
                basicMusic.setUpdateTime(new Date());

                kugouMusicDTO.setMusic(basicMusic);

                BasicAlbum basicAlbum = new BasicAlbum();
                basicAlbum.setAlbumName(albumName);
                basicAlbum.setUpdateTime(new Date());
                //专辑封面地址+专辑介绍
                String albumId = (String) dataJson.get("album_id");
                String albumUrl0 = null;
                try {
                    Document albumDocument = Jsoup.connect(albumUrl+albumId+".html").get();
                    Element albumDescriptionElement = albumDocument.select("span:contains(简介)").first().parent();
                    basicAlbum.setAlbumDescription(albumDescriptionElement.text());

                    Elements albumImgUrl = albumDocument.select("body")
                            .select("div.wrap.alm2.clear_fix")
                            .select("div.l")
                            .select("div.pic");
                    for (Element element : albumImgUrl) {
                        String html = element.html();
                        albumUrl0 = html.substring(html.indexOf("_src=")+6,html.indexOf(".jpg")+4);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //下载
                String musicAddress = downLoad(songUrl, songName,downloadMusicSrc + songName + ".mp3");
                String imgAddress = downLoad(imgUrl, songName,downloadImgSrc + songName + ".jpg");
                String albumImgAddress = downLoad(albumUrl0,albumName,downloadAlbumImgSrc + songName + ".jpg");

                basicMusic.setMusicAddress(musicAddress);
                basicMusic.setImgAddress(imgAddress);
                basicAlbum.setImgAddress(albumImgAddress);

                kugouMusicDTO.setAlbum(basicAlbum);

                spiderService.saveDTO(kugouMusicDTO);

                kugouMusicDTOList.add(kugouMusicDTO);
            }
        }
        return kugouMusicDTOList;
    }

    public String downLoad(String musicUrl, String musicName,String target) {
        try {
            log.info("\n" + musicName + "源地址->" + musicUrl);
            Connection.Response response = Jsoup.connect(musicUrl)
                    //忽略ContentType
                    .ignoreContentType(true)
                    //解除最大字节限制
                    .maxBodySize(0)
                    //模拟浏览器用户代理
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    //执行
                    .execute();
            ByteArrayInputStream stream = new ByteArrayInputStream(response.bodyAsBytes());
            FileUtils.copyInputStreamToFile(stream, new File(target));
            return target;
        } catch (Exception e) {
            log.info("链接->" + musicUrl + " ＞﹏＜ 下载失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Doc工具类
     *
     * @param url
     * @return
     * @throws Exception
     */
    public Document parsUrl(String url) {
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(2000)
                    .get();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 正则匹配字符串集合工具类
     *
     * @param str
     * @param keyWords
     * @return
     */
    public List<String> myRegexpList(String str, String keyWords) {
        List<String> list = new ArrayList<>();
        Pattern compile = compile(keyWords);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }

    private Pattern compile(String keyWords) {
        return Pattern.compile(keyWords);
    }

    /**
     * 正则匹配单个字符串工具类
     *
     * @param str
     * @param keyWords
     * @return
     */
    public String myRegexpOne(String str, String keyWords) {
        String result = "";
        Pattern compile = compile(keyWords);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    /**
     * unicode转码中文字符
     *
     * @param str
     * @return
     */
    public String unicodeToString(String str) {
        Pattern pattern = compile(unicodeRegex);
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
