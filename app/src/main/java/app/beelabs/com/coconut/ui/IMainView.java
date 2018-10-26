package app.beelabs.com.coconut.ui;

import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.codebase.base.IView;

public interface IMainView extends IView {

    void handleData(SourceResponse model);

}
