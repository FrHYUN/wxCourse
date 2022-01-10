package entity;

import lombok.Data;

import java.util.Date;

@Data
public class CardRecord {

    private int recordid;

    private int cardid;

    private Date time;

    private int value;

    private int type;

    private String event;

    public CardRecord(int cardid, Date time, int value, int type, String event) {
        this.cardid = cardid;
        this.recordid = recordid;
        this.time = time;
        this.value = value;
        this.type = type;
        this.event = event;
    }

    public CardRecord() {

    }
}
