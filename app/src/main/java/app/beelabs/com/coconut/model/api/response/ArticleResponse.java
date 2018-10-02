package app.beelabs.com.coconut.model.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import app.beelabs.com.coconut.model.pojo.Article;
import app.beelabs.com.codebase.base.response.BaseDataResponse;
import app.beelabs.com.codebase.base.response.BaseResponse;

/**
 * Created by arysuryawan on 8/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleResponse extends BaseResponse {
    private DataResponse data;
    private String source;
    private String sortBy;


    private List<Article> articles;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public DataResponse getBaseData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DataResponse extends BaseDataResponse {

    }
}
