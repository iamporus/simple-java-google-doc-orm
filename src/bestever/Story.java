package bestever;

public class Story {

    private String submitter;
    
    private String story;

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
 
    @Override
    public String toString(){
        return "[" + submitter + "," + story + "]";
    }
}
