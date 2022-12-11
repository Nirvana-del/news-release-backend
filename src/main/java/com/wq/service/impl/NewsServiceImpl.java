package com.wq.service.impl;

import com.wq.entity.News;
import com.wq.entity.NewsExample;
import com.wq.mapper.NewsMapper;
import com.wq.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wq.util.Tool.htmlUnescape;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    /**
     * 获取所有新闻列表
     * @return
     */
    @Override
    public List<News> getNewsList() {
        NewsExample newsExample = new NewsExample();
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        System.out.println(newsList);
        htmlUnescape(newsList);
        System.out.println(newsList);
        return newsList;
    }

    /**
     * 添加新闻
     * @param news
     */
    @Override
    public void addNews(News news) {
        newsMapper.insertSelective(news);
    }

    /**
     * 更新新闻
     * @param id
     * @param news
     */
    @Override
    public void updateNews(String id, News news) {
        newsMapper.updateByPrimaryKeySelective(news);
    }

    /**
     * 删除新闻
     * @param id
     */
    @Override
    public void deleteNews(String id) {
        newsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取当前用户草稿列表
     * @param author
     * @return
     */
    @Override
    public List<News> getDraftsList(String author) {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andAuthorEqualTo(author);
        criteria.andAuditStateEqualTo(0);
        newsExample.setOrderByClause("create_time desc,publish_time desc");
        List<News> draftList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(draftList);
        return draftList;
    }

    /**
     * 获取当前用户审核列表
     * @param author
     * @return
     */
    @Override
    public List<News> getAuditList(String author) {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andAuthorEqualTo(author);
        // 审核状态不为 0（草稿箱）
        criteria.andAuditStateNotEqualTo(0);
        // 发布状态小于 2（已发布）
        criteria.andPublishStateLessThanOrEqualTo(1);
        List<News> auditList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(auditList);
        return auditList;
    }

    /**
     * 获取所有审核列表
     * @return
     */
    @Override
    public List<News> getAllAuditList() {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andAuditStateEqualTo(1);
        List<News> auditList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(auditList);
        return auditList;
    }

    /**
     * 通过发布状态获取当前用户的新闻列表
     * @param publishState
     * @param author
     * @return
     */
    @Override
    public List<News> getNewsListByPublishState(Integer publishState, String author) {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andPublishStateEqualTo(publishState);
        criteria.andAuthorEqualTo(author);
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(newsList);
        return newsList;
    }

    /**
     * 获取所有已下线的新闻列表
     * @return
     */
    @Override
    public List<News> getSunsetList() {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        // 发布状态为 3（已下线）
        criteria.andPublishStateEqualTo(3);
        List<News> sunsetList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(sunsetList);
        return sunsetList;
    }

    /**
     * 获取所有已下线的新闻列表
     * @return
     */
    @Override
    public List<News> getPublishedList() {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        // 发布状态为 2（已发布）
        criteria.andPublishStateEqualTo(2);
        List<News> sunsetList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(sunsetList);
        return sunsetList;
    }

    /**
     * 获取新闻详情
     * @param id
     * @return
     */
    @Override
    public News getNewsDetail(String id) {
        News news = newsMapper.selectByPrimaryKey(id);
        htmlUnescape(news);
        return news;
    }

    /**
     * 获取用户最常浏览的新闻列表
     * @return
     */
    @Override
    public List<News> getMostBrowsingNews() {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andPublishStateEqualTo(2);
        newsExample.setOrderByClause("view desc");
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(newsList);
        return newsList;
    }

    /**
     * 获取用户点赞最多的新闻列表
     * @return
     */
    @Override
    public List<News> getMostLikesNews() {
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andPublishStateEqualTo(2);
        newsExample.setOrderByClause("star desc");
        List<News> newsList = newsMapper.selectByExampleWithBLOBs(newsExample);
        htmlUnescape(newsList);
        return newsList;
    }


}
