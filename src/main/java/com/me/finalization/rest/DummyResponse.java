package com.me.finalization.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by puspendu on 8/31/16.
 */
@XmlRootElement
public class DummyResponse {
    public String name;
    public Integer id;

    public DummyResponse(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public DummyResponse() {
    }

}
