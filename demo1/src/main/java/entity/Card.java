package entity;


import lombok.Data;

@Data
public class Card {


    private int cardid;

    private int balance;

    private int userid;

    public Card(int userid) {
        this.userid = userid;
    }
}
