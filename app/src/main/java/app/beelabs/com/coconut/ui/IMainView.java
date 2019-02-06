package app.beelabs.com.coconut.ui;

import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.codebase.base.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;

public interface IMainView extends IView {

    void handleProcessing();
    void handleDataSource(SourceResponse model);
    void handleDataUpload(BaseResponse model);

    void handleProfileDone(ProfileResponseModel model);



}
