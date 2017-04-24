package com.ajoshow.mock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by andychu on 2017/4/22.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    // data
    private String value;
    private String label;

    @Column(name="data_value", length=1000)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name="data_label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        if(value != null)
            sb.append("value='").append(value).append('\'');

        if(label != null)
            sb.append(", label='").append(label).append('\'');

        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (value != null ? !value.equals(data.value) : data.value != null) return false;
        return label != null ? label.equals(data.label) : data.label == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
