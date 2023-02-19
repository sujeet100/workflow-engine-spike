package com.example.demo.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "process_def", schema = "public", catalog = "workflow")
public class ProcessDef {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "process_id", nullable = false)
    private int processId;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy="processId")
    private Set<StepDef> steps;

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StepDef> getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProcessDef that = (ProcessDef) o;
        return processId == that.processId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, name);
    }
}
