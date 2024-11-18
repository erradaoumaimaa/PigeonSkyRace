package com.pigeonskyrace.dto.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pigeonskyrace.model.SaisonPigeon;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class SaisonPigeonResponseDTO {

    @JsonProperty("id")
    public String getId() {
        return id.toHexString();
    }
    private ObjectId id;

    @JsonProperty("saisonId")
    public String getSaisonId() {
        return saisonId.toHexString();
    }
    private ObjectId saisonId;

    @JsonProperty("pigeonId")
    public String getPigeonId() {
        return pigeonId.toHexString();
    }
    private ObjectId pigeonId;


    public SaisonPigeon toEntity() {
        SaisonPigeon saisonPigeon = new SaisonPigeon();
        saisonPigeon.setPigeonId(this.pigeonId);
        saisonPigeon.setSaisonId(this.saisonId);
        return saisonPigeon;
    }
}
