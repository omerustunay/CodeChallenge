package com.example.dovizuygulamasi.Pagination;

import com.example.dovizuygulamasi.Models.ApiModel;

import java.util.ArrayList;

public class Pagination {

    private final int itemPerPage, lastPageItems,lastPage;
    private final ArrayList<ApiModel> data;
    public static int totalItems;


    public Pagination(int itemPerPage, ArrayList<ApiModel> data){
        this.itemPerPage=itemPerPage;
        this.data=data;
        this.totalItems = data.size();
        this.lastPage= totalItems/itemPerPage;
        this.lastPageItems = totalItems%itemPerPage;
    }


    public int getTotalPages(){

        int remainingItems = totalItems%itemPerPage;

        if (remainingItems > 0) {

            return totalItems/itemPerPage;
        }
        return (totalItems/itemPerPage)-1;

    }
    public ArrayList<ApiModel> generateData(int currentPage) {
        int startItem = currentPage * itemPerPage;
        int lastItem = startItem + itemPerPage;

        ArrayList<ApiModel> currentBorsaDeger = new ArrayList<>();

        try{
            for(int i = 0; i<data.size(); i++){
                if(i >= startItem && i<lastItem){
                    currentBorsaDeger.add(data.get(i));
                }
            }
            return currentBorsaDeger;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
