package ramajudicial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceApiDirectoryDTO {

    private String nombre;
    private String especialidad;
    private String agrupacion;
    private String url;

}
