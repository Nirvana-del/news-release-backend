package com.wq.service;

import com.wq.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsList();

    void addNews(News news);

    void updateNews(String id, News news);

    void deleteNews(String id);

    List<News> getDraftsList(String username);

    List<News> getAuditList(String author);

    List<News> getAllAuditList();

    List<News> getNewsListByPublishState(Integer publishState, String author);

    List<News> getSunsetList();

    News getNewsDetail(String id);

    List<News> getMostBrowsingNews();

    List<News> getMostLikesNews();

    List<News> getPublishedList();
}
