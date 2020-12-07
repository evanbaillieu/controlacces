/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.controleacces.client.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author vins
 */
@FacesConverter(value = "PersonneConverter")
public class PersonneConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uic, String persId) {
        long value = Long.valueOf(persId);
        return value;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }

}
