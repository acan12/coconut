package app.beelabs.com.coconut.model.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.beelabs.com.codebase.base.response.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryResponse extends BaseResponse {
    private int achievementDay;

    public int getAchievementDay() {
        return achievementDay;
    }

    public void setAchievementDay(int achievementDay) {
        this.achievementDay = achievementDay;
    }
}
