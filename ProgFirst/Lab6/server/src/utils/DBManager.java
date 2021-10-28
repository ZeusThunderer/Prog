package utils;

import exception.NoDataException;
import exception.NoSuchStatementException;
import exchange.CommandStatus;
import exchange.Request;
import exchange.User;
import stdgroup.Coordinates;
import stdgroup.Person;
import stdgroup.RawGroup;
import stdgroup.StudyGroup;
import stdgroup.enums.Country;
import stdgroup.enums.EyeColor;
import stdgroup.enums.HairColor;
import stdgroup.enums.Semester;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Properties;

public class DBManager {
    private final Connection connection;
    private final Statement statement;

    public DBManager() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://pg:5432/studs";
        Properties props = new Properties();
        props.setProperty("user","s305246");
        props.setProperty("password","bet699");
        connection = DriverManager.getConnection(url, props);
        statement = connection.createStatement();
    }

    public CommandStatus check(User user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("select * from users where login = ?");
        stm.setString( 1, user.getLogin() );
        if (!user.getNewUser()){
            if  (stm.executeUpdate() < 1)
                return CommandStatus.WRONG_USERNAME;
            else if (checkUser( user ))
                return CommandStatus.WRONG_PASSWORD;
        }
        if (user.getNewUser())
            addUser( user );
        return CommandStatus.LOGGED;
    }

    public boolean checkUser(User user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("select * from users where login = ? and password = ?");
        stm.setString( 1, user.getLogin() );
        stm.setString( 2, user.getLogin() );
        if (stm.executeUpdate() < 1)
            return false;
        return true;
    }
    public void addUser(User user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement( "insert into users (login, password) " + "values(?,?)" );
        stm.setString( 1, user.getLogin() );
        stm.setString( 2, user.getPassword() );
        stm.executeUpdate();
    }

    public StudyGroup add(Request requestData) throws SQLException, NoDataException {
        PreparedStatement stm = connection.prepareStatement("insert into studs " +
                "(id,name, x, y, creationDate, studentsCount, expelledStudents, averageMark, semester, person_name, birthday, eyecolor, haircolor, nationality,owner) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        StudyGroup gr = setDataToStatement(requestData.getRawGroup(), stm);
        gr.setOwner( requestData.getLogin() );
        stm.setString(15, requestData.getLogin());
        stm.executeUpdate();
        return gr;
    }

    public void update(Request request) throws SQLException, NoDataException, NoSuchStatementException {
        PreparedStatement stm = connection.prepareStatement("update studs set " +
                "name = ?," +
                "x = ?," +
                "y = ?," +
                "creationdate = ?," +
                "studentscount = ?," +
                "expelledstudents = ?," +
                "averagemark = ?," +
                "semester = ?," +
                "person_name = ?," +
                "birthday = ?," +
                "eyecolor = ?," +
                "haircolor = ?," +
                "nationality = ?" +
                "where id = ? and owner = ?");
        updateStatement( request.getRawGroup(), stm );
        stm.setInt(14,(int) request.getObject());
        stm.setString(15, request.getUser().getLogin());
        if (stm.executeUpdate() < 1) {
            throw new NoSuchStatementException();
        }
    }
    public void updateStatement(RawGroup group, PreparedStatement stm) throws SQLException {
        stm.setString(1, group.getName());
        stm.setInt(2, group.getCoordinates().getX());
        stm.setInt(3, group.getCoordinates().getY());
        stm.setTimestamp(4, Timestamp.valueOf( LocalDateTime.now()));
        stm.setInt(5, group.getStudentsCount());
        stm.setInt(6, group.getExpelledStudents());
        stm.setFloat(7, group.getAverageMark());
        stm.setString(8, group.getSemesterEnum().toString());
        stm.setString(9, group.getGroupAdmin().getName());
        stm.setDate(10, (java.sql.Date) group.getGroupAdmin().getBirthday() );
        stm.setString(11, group.getGroupAdmin().getEyeColor().toString());
        stm.setString(12, group.getGroupAdmin().getHairColor().toString());
        stm.setString(13, group.getGroupAdmin().getNationality().toString());
    }

    public void remove(Request request) throws SQLException, NoSuchStatementException {
        PreparedStatement stm = connection.prepareStatement("delete from studs where id = ? and owner = ?");
        stm.setInt(1,Integer.valueOf((String) request.getObject()));
        stm.setString(2, request.getUser().getLogin());
        if (stm.executeUpdate() < 1) {
            throw new NoSuchStatementException();
        }
    }

    public void clear() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("delete from studs");
        stm.executeUpdate();
    }

    public void load(LinkedHashSet<StudyGroup> groups) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from studs");
        while (rs.next()){
            //build
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
            LocalDateTime time = LocalDateTime.parse(rs.getString( "creationdate" ), formatter);
            int studentsCount = rs.getInt("studentscount");
            int expelledStudents = rs.getInt("expelledstudents");
            Float averageMark = rs.getFloat("averagemark");
            Semester semester = Semester.valueOf(rs.getString("semester"));
            String person_name = rs.getString("person_name");
            Date date = rs.getDate("birthday");
            EyeColor eyeColor = EyeColor.valueOf( rs.getString("eyecolor" ) );
            HairColor hairColor = HairColor.valueOf( rs.getString("haircolor" ) );
            Country nationality = Country.valueOf( rs.getString("nationality" ) );
            String  owner = rs.getString( "owner" );
            //add
            groups.add(
                    new StudyGroup(
                        id,
                        name,
                        new Coordinates( x,y ),
                        time,
                        studentsCount,
                        expelledStudents,
                        averageMark,
                        semester,
                        new Person(
                                person_name,
                                date,
                                eyeColor,
                                hairColor,
                                nationality
                        ),
                        owner
                    )
            );
        }
    }

    public int getId() throws SQLException {
        ResultSet rs = statement.executeQuery("select nextval('id_gen')");
        if (rs.next())
            return rs.getInt( "nextval" );
        return 1337;
    }

    private StudyGroup setDataToStatement(RawGroup group, PreparedStatement stm) throws SQLException, NoDataException {
        if (group != null) {
            int id = getId();
            LocalDateTime time = LocalDateTime.now().withNano( 0 );
            stm.setInt( 1, id);
            stm.setString(2, group.getName());
            stm.setInt(3, group.getCoordinates().getX());
            stm.setInt(4, group.getCoordinates().getY());
            stm.setTimestamp(5, Timestamp.valueOf(time));
            stm.setInt(6, group.getStudentsCount());
            stm.setInt(7, group.getExpelledStudents());
            stm.setFloat(8, group.getAverageMark());
            stm.setString(9, group.getSemesterEnum().toString());
            stm.setString(10, group.getGroupAdmin().getName());
            stm.setDate(11, new java.sql.Date(group.getGroupAdmin().getBirthday().getTime()) );
            stm.setString(12, group.getGroupAdmin().getEyeColor().toString());
            stm.setString(13, group.getGroupAdmin().getHairColor().toString());
            stm.setString(14, group.getGroupAdmin().getNationality().toString());
            return new StudyGroup(id,time,group);
        } else throw new NoDataException();
    }
}
