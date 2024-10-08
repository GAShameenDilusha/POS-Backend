package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO implements Serializable {
    private String order_id;
    private String customer_id;
    private String total;
    private String date;

    private lk.ijse.dto.ItemDTO[] items;
}