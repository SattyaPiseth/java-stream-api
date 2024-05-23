package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Sattya
 * create at 5/23/2024 3:34 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Integer id;
    private String title;
    private List<String> instructorName;
    private List<String> requirement;
    private Date startDate;
}
