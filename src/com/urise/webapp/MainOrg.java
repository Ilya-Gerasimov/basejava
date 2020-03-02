package com.urise.webapp;

import com.urise.webapp.model.Organization;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MainOrg {
    public static void main(String[] args) {

        List<Organization.Position> positions = new ArrayList<>();
        positions.add( new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY,
                "Аспирантура (программист С, С++)", ""));
        positions.add( new Organization.Position(1987, Month.JUNE, 1993, Month.JULY,
                "Инженер (программист Fortran, C)", ""));
        positions.add( new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                "Выпускник школы", ""));

        System.out.println(positions.toString());
        positions.add( new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                "Выпускник школы2", ""));
        System.out.println(positions.toString());
        positions.clear();
        System.out.println(positions.toString());

        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        methodA(ints);
        System.out.println(ints.size()); //*что тут выведет в консоль?*
        System.out.println(ints.toString());
        methodB(ints);
        System.out.println(ints.size()); //*что тут выведет в консоль?*
        System.out.println(ints.toString());

    }
    static void methodA(List<Integer> ints) {
        ints.add(2);
    }
    static void methodB(List<Integer> ints) {
        ints = new ArrayList<>();
        ints.add(3);
    }
}
