package lml.snir.controleacces.metier.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.snir.controleacces.metier.entity.Day;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2020-12-10T19:10:56")
@StaticMetamodel(TimeSlot.class)
public class TimeSlot_ { 

    public static volatile SingularAttribute<TimeSlot, Integer> endHour;
    public static volatile SingularAttribute<TimeSlot, Integer> beginHour;
    public static volatile SingularAttribute<TimeSlot, Day> beginDay;
    public static volatile SingularAttribute<TimeSlot, Day> endDay;
    public static volatile SingularAttribute<TimeSlot, Integer> beginMinutes;
    public static volatile SingularAttribute<TimeSlot, Long> id;
    public static volatile SingularAttribute<TimeSlot, Integer> endMinutes;

}