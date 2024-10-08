package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Follow the bean spec...
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements Serializable {
    private String customer_id;
    private String name;
    private String address;
    private String contact;


}
