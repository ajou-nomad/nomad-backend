package backend.nomad.dto.store;

public class SalesDto {

    private Integer month;
    private Double total;

    public SalesDto(Integer month, Double total) {
        this.month = month;
        this.total = total;
    }
}
