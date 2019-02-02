package com.example.gridviewtest;

public class GridViewEntity {
    private String name;
    private String[] shifts;
    private String note;
    private boolean[] isCurrentCell;
    public GridViewEntity(){
        name = "";
        shifts = new String[7];
        note="";
        isCurrentCell = new boolean[9];
        for(int i = 0;i<isCurrentCell.length;i++){
            isCurrentCell[i]=false;
        }
    }
    public GridViewEntity(String _name,String[] _shifts,String _note){
        name = _name;
        shifts = _shifts;
        note=_note;
        isCurrentCell = new boolean[9];
        for(int i = 0;i<isCurrentCell.length;i++){
            isCurrentCell[i]=false;
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getShifts() {
        return shifts;
    }

    public void setShifts(String[] shifts) {
        this.shifts = shifts;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean[] isCurrentCell() {
        return isCurrentCell;
    }

    public void setCurrentCell(boolean[] currentCell) {
        isCurrentCell = currentCell;
    }
    public void setChecked(int y,boolean flag){
        clearChecked();
        isCurrentCell[y] = flag;
    }
    public void clearChecked(){
        for(int i = 0;i < 9;i++){
            isCurrentCell[i] = false;
        }
    }
    public void setShift(int y,String s){
        shifts[y-1] = s;
    }
}
