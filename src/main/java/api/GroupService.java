package api;

import entities.Group;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupService {

  private final Database db = new PsqlDB();

  public List<Group> getGroupsByDeviceId(String deviceId) throws SQLException, ClassNotFoundException {
    db.openConnection();

    String query = String.format("SELECT groups.* FROM walkers_groups " +
            "INNER JOIN walkers ON walkers.device_id = walkers_groups.walker_id " +
            "INNER JOIN groups ON groups.id = walkers_groups.group_id WHERE walkers.device_id = '%s'", deviceId);

    List<Group> groups = Group.fromString(db.executeSelectQuery(query));
    db.closeConnection();
    return groups;
  }

  public List<Group> getAllGroups() throws SQLException, ClassNotFoundException {
    db.openConnection();
    List<Group> groups = Group.fromString(db.executeSelectQuery("SELECT * FROM groups"));
    db.closeConnection();
    return groups;
  }

  public boolean addNewGroup(Group group) throws SQLException, ClassNotFoundException {
    return false;
  }
}
