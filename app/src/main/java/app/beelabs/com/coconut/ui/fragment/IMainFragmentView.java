package app.beelabs.com.coconut.ui.fragment;

import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.codebase.base.IView;

public interface IMainFragmentView extends IView {

    void handleDataSummary(SummaryResponse model);

}
