package com.wq.controller;

import com.wq.entity.News;
import com.wq.service.impl.NewsServiceImpl;
import com.wq.util.Result;
import com.wq.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

import static com.wq.util.Tool.newsExpandCategory;
import static com.wq.util.Tool.newsExpandRole;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*")
public class NewsController {
    @Autowired
    NewsServiceImpl newsServiceImpl;

    Tool tool = new Tool();
    /**
     * 新闻列表
     * @return
     */
    @GetMapping("/list")
    public Result getNewsList(){
        try {
            List<News> newsList = newsServiceImpl.getNewsList();
            Map<String, List<News>> map = new HashMap<>();
            map.put("newsList", newsList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 当前用户草稿箱列表
     * @return
     */
    @GetMapping("/draft_list")
    public Result getDraftsList(@RequestParam("author") String author){
        System.out.println(author);
        try {
            List<News> draftList = newsServiceImpl.getDraftsList(author);
            newsExpandCategory(draftList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("draftList", draftList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }
    /**
     * 当前用户审核列表
     * @return
     */
    @GetMapping("/audit_list")
    public Result getAuditList(@RequestParam("author") String author){
        System.out.println(author);
        try {
            List<News> auditList = newsServiceImpl.getAuditList(author);
            newsExpandCategory(auditList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("auditList", auditList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 当前处于审核状态下的新闻列表
     * 待修改
     * @return
     */
    @GetMapping("/drafts")
    public Result getAllAuditList(){
        try {
            List<News> audits = newsServiceImpl.getAllAuditList();
            newsExpandCategory(audits);
            Map<String, List<News>> map = new HashMap<>();
            map.put("audits", audits);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 所有已下线的新闻列表
     * 待修改
     * @return
     */
    @GetMapping("/sunset_list")
    public Result getSunsetList(){
        try {
            List<News> sunsetList = newsServiceImpl.getSunsetList();
            newsExpandCategory(sunsetList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("sunsetList", sunsetList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 获取所有已发布的新闻列表
     * 待修改
     * @return
     */
    @GetMapping("/published_list")
    public Result getPublishedList(){
        try {
            List<News> publishedList = newsServiceImpl.getPublishedList();
            newsExpandCategory(publishedList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("publishedList", publishedList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 通过发布状态获取当前用户的新闻列表
     * @return
     */
    @GetMapping("/publish_list/{publishState}")
    public Result getNewsListByPublishState(@PathVariable Integer publishState,
                                            @RequestParam("author") String author){
        System.out.println(publishState);
        System.out.println(author);
        try {
            List<News> newsList = newsServiceImpl.getNewsListByPublishState(publishState, author);
            newsExpandCategory(newsList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("newsList", newsList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 获取用户最常浏览的新闻列表
     * @return
     */
    @GetMapping("/most_browsing")
    public Result getMostBrowsingNews(){
        try {
            List<News> newsList = newsServiceImpl.getMostBrowsingNews();
            if(newsList.size() >= 6){
                newsList = newsList.subList(0, 5);
            }
            newsExpandCategory(newsList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("newsList", newsList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 获取用户点赞最多的新闻列表
     * @return
     */
    @GetMapping("/most_like")
    public Result getMostLikesNews(){
        try {
            List<News> newsList = newsServiceImpl.getMostLikesNews();
            if(newsList.size() >= 6){
                newsList = newsList.subList(0, 5);
            }
            newsExpandCategory(newsList);
            Map<String, List<News>> map = new HashMap<>();
            map.put("newsList", newsList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @PostMapping
    public Result addNews(News news){
        Map<String, String> map = new HashMap<>();
        try {
            // 使用 UUID 生成新闻 ID
            String newsId = UUID.randomUUID().toString();
            news.setId(newsId);
            String content = news.getContent();
            // 把html的特殊字符转换成符合Intel HEX文件的字符串
            String temp = HtmlUtils.htmlEscapeHex(content);
            news.setContent(temp);
            // 调用服务层接口
            newsServiceImpl.addNews(news);
            // 返回前端成功的  Map 集合
            map.put("status", "success");
//            System.out.println("要插入到数据库中的新闻内容");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 修改新闻内容
     * @param id
     * @param news
     * @return
     */
    @PatchMapping("/{id}")
    public Result updateNews(@PathVariable String id,News news){
        System.out.println(id);
        System.out.println(news);
        Map<String, String> map = new HashMap<>();
        try {
            newsServiceImpl.updateNews(id, news);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteNews(@PathVariable String id){
        System.out.println(id);
        Map<String, String> map = new HashMap<>();
        try {
            newsServiceImpl.deleteNews(id);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

    /**
     * 新闻详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getNewsDetail(@PathVariable String id){
        System.out.println(id);
        try {
            Map<String, News> map = new HashMap<>();
            News newsDetail = newsServiceImpl.getNewsDetail(id);
            newsExpandRole(newsDetail);
            newsExpandCategory(newsDetail);
            map.put("newsDetail", newsDetail);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            return Result.error(null);
        }
    }

}
