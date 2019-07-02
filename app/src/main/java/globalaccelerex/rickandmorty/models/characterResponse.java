package globalaccelerex.rickandmorty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class characterResponse {

    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<cResult> results = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<cResult> getResults() {
        return results;
    }

    public void setResults(List<cResult> results) {
        this.results = results;
    }



}
