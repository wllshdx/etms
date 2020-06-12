package cn.wllnb.etms.dao.manage;

import cn.wllnb.etms.dao.news.NewsDAO;
import cn.wllnb.etms.model.bo.NewsBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NewsManage {
    private static final int CRAWL_INTERVAL = 60 * 60 * 1000;
    private static final int CRAWL_TIMEOUT = 30 * 1000;
    private static final String CRAWL_TARGET_URL = "http://www.bkjy.sdnu.edu.cn/xszq1.htm";
    private static final String BASE_URL = "http://www.bkjy.sdnu.edu.cn/";

    @Autowired
    private NewsDAO newsDAO;

    public List<NewsBO> getAllNews() {
        Map<String, String> map = newsDAO.getAllNews();

        List<NewsBO> newsList = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            String value = map.get(key);
            // 2019/01/01http://host/path
            String date = value.substring(0, 10);
            String url = value.substring(10);
            newsList.add(new NewsBO(key, date, url));
        }

        return newsList;
    }

    @Scheduled(fixedDelay = CRAWL_INTERVAL)
    public void crawlNews() {
        Document pageDoc = fetchPage();
        if (pageDoc == null) {
            return;
        }

        List<NewsBO> newsList = parseNews(pageDoc);
        newsDAO.clear();
        for (NewsBO news : newsList) {
            newsDAO.addNews(news.getTitle(), news.getDate() + news.getUrl());
        }
    }

    private Document fetchPage() {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(CRAWL_TARGET_URL), CRAWL_TIMEOUT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return doc;
    }

    private List<NewsBO> parseNews(Document pageDoc) {
        Elements elements = pageDoc.body()
                .getElementsByClass("TB3").get(0)
                .getElementsByTag("table").get(0)
                .getElementsByTag("tr");

        List<NewsBO> newsList = new ArrayList<>();
        for (Element element : elements) {
            if (!element.attr("id").startsWith("line")) {
                continue;
            }

            Element aTag = element.getElementsByTag("a").get(0);
            Element dateTag = element.getElementsByTag("td").get(2);
            String url = BASE_URL + aTag.attr("href");
            String title = aTag.text();
            String date = dateTag.text();
            newsList.add(new NewsBO(title, date, url));
        }

        return newsList;
    }
}
