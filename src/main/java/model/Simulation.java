package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Simulation {
    private UUID id;
    private String name;
    private String parametersJson;
    private String resultJson;
    private SimulationStatus status;
    private LocalDateTime createdAt;

    public Simulation() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.status = SimulationStatus.PENDING;
    }

    // getters y setters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getParametersJson() { return parametersJson; }
    public void setParametersJson(String parametersJson) { this.parametersJson = parametersJson; }
    public String getResultJson() { return resultJson; }
    public void setResultJson(String resultJson) { this.resultJson = resultJson; }
    public SimulationStatus getStatus() { return status; }
    public void setStatus(SimulationStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
