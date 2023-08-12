package com.example.studyeffectivejavapart1.chpater02.item13.code.clone_use_constructor;

public class SubItem extends Item implements Cloneable {

    private String name;

    @Override
    public SubItem clone() {
        return (SubItem) super.clone();
    }

    public static void main(String[] args) {
        SubItem item = new SubItem();
        SubItem clone = item.clone();

        System.out.println(clone != item);
        System.out.println(clone.getClass() == item.getClass());
        System.out.println(clone.equals(item));
    }
}
