/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.TimeSlot;

/**
 *
 * @author saturne
 */
class TimeSlotDataServiceJPAImpl extends AbstracCrudServiceJPA<TimeSlot> implements TimeSlotDataService {

    public TimeSlotDataServiceJPAImpl(String PU) {
        super(PU);
    }

}
