
public class Questions
{
long question_id; int type;
String title, description,option_1,option_2,option_3,option_4;
boolean[] answer=new boolean[5];

public Questions(long question_id, int type, String title, String description, String option_1,
String option_2, String option_3, String option_4, boolean[] answer) {
super();
this.question_id = question_id;
this.type = type;
this.title = title;
this.description = description;
this.option_1 = option_1;
this.option_2 = option_2;
this.option_3 = option_3;
this.option_4 = option_4;
this.answer = answer;
}
}

