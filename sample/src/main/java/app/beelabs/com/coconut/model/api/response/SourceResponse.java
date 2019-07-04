package app.beelabs.com.coconut.model.api.response;

import java.util.List;

import app.beelabs.com.coconut.model.pojo.Source;
import app.beelabs.com.codebase.base.response.BaseResponse;
/**
 * Created by arysuryawan on 8/19/17.
 */

public class SourceResponse extends BaseResponse {

    private List<Source> sources;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
