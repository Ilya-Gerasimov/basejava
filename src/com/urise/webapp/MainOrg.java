package com.urise.webapp;

import com.urise.webapp.model.Link;
import com.urise.webapp.model.Organization;
import com.urise.webapp.model.OrganizationSection;
import com.urise.webapp.model.Resume;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.urise.webapp.model.SectionType.EDUCATION;

public class MainOrg {
    public static void main(String[] args) {
        final Resume RESUME = new Resume("uuid", "Name1");

        List<Organization.Position> positions = new ArrayList<>();
        positions.add( new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY,
                "Аспирантура (программист С, С++)", ""));
        positions.add( new Organization.Position(1987, Month.JUNE, 1993, Month.JULY,
                "Инженер (программист Fortran, C)", ""));
        positions.add( new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                "Выпускник школы", ""));

        List<Organization> Education = new ArrayList<>();
        Education.add(new Organization(new Link("Санкт-Петербургский национальный исследовательский университет",
                "https://itmo.ru/ru/"), positions));
        RESUME.addSection(EDUCATION, new OrganizationSection(Education));

        System.out.println("RESUME 1\n" + RESUME.toString() + "\n");

        positions.add( new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                "Выпускник школы 2", ""));

        Education.add(new Organization(new Link("Санкт-Петербургский национальный исследовательский университет",
                "https://itmo.ru/ru/"), positions));
        RESUME.addSection(EDUCATION, new OrganizationSection(Education));

        System.out.println("RESUME 1\n" + RESUME.toString() + "\n");



    }
}
