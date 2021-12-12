package content;

public abstract class Content implements IContent {
	
	private int arrivalDay;  //holds arrivalDay as a int
    private int contentId;   //holds contentId as a int
    private String name;     //holds name as a String
    private int duration;    //holds duration as a int

    //default constructor
    public Content(){
        this(0,0,"",0);
    }

    //full arguments constructors
    public Content(int arrivalDay, int contentId, String name, int duration){
        this.arrivalDay = arrivalDay;
        this.contentId = contentId;
        this.name = name;
        this.duration = duration;

    }
    //starts getter methods
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
    //ends getter methods

    public void setContentId(int contentId){
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "*Arrival Day: " + getArrivalDay() + " *Content Id: " + getContentId() + " *Name: " + getName();
    }

   

}
