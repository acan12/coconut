package app.beelabs.com.coconut.model.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import app.beelabs.com.codebase.base.response.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryResponse extends BaseResponse {
    private int achievementDay;

    @JsonProperty("DescriptionCode")
    private String descriptionCode;
    private List<AcquisitionModel> acquisitionData;

    public int getAchievementDay() {
        return achievementDay;
    }

    public void setAchievementDay(int achievementDay) {
        this.achievementDay = achievementDay;
    }

    public String getDescriptionCode() {
        return descriptionCode;
    }

    public void setDescriptionCode(String descriptionCode) {
        this.descriptionCode = descriptionCode;
    }

    public List<AcquisitionModel> getAcquisitionData() {
        return acquisitionData;
    }

    public void setAcquisitionData(List<AcquisitionModel> acquisitionData) {
        this.acquisitionData = acquisitionData;
    }
}
