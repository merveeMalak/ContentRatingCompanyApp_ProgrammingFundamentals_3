package content;

public abstract class Content implements IContent {
	
	private int arrivalDay;
    private int contentId;
    private String name;
    private int duration;
    private double averageRating;

    public Content(){
        this(0,0,"",0,0);
    }

    public Content(int arrivalDay, int contentId, String name, int duration, double averageRating){
        this.arrivalDay = arrivalDay;
        this.contentId = contentId;
        this.name = name;
        this.duration = duration;
        this.averageRating = averageRating;
    }
/*
    public Content(Content content){
        this(content.arrivalDay, content.contentId, content.name, content.duration, content.averageRating);
    }
*/
    public int getArrivalDay() {
        return arrivalDay;
    }

    public int getContentId() {
        return contentId;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setContentId(int contentId){
        this.contentId = contentId;
    }
    @Override
    public String toString() {
        return "*Arrival Day: " + getArrivalDay() + " *Content Id: " + getContentId() + " *Name: " + getName() + " *Average Rating: " + getAverageRating();
    }

    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        else if (getClass() != other.getClass()){
            return false;
        }
        Content otherContent = (Content) other;
        return ((this.arrivalDay == otherContent.arrivalDay) &&
                (this.contentId == otherContent.contentId) &&
                (this.name.equals(otherContent.name)) &&
                (this.duration == otherContent.duration) &&
                (this.averageRating == otherContent.averageRating));
    }

    public abstract double calculateCritic(int criticOption);

}
