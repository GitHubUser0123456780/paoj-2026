package com.pao.laboratory14.exercise2.repository;

import com.pao.laboratory14.exercise1.TipBilet;
import com.pao.laboratory14.exercise2.model.Eveniment;
import com.pao.laboratory14.exercise2.util.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

public class EvenimentRepository implements Repository<Eveniment, Integer> {
    private final Connection conn;
    public EvenimentRepository() throws Exception{
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public void initSchema() throws SQLException{
        String dropTable = """
                DROP TABLE IF EXISTS EVENIMENTE
            """;
        String createTable = """
                CREATE TABLE IF NOT EXISTS EVENIMENTE(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nume TEXT NOT NULL,
                    data TEXT NOT NULL,
                    capacitate INTEGER,
                    tip TEXT
                )
            """;
        try (Statement st = conn.createStatement()){
            st.execute(dropTable);
            st.execute(createTable);
        }
    }

    @Override
    public void save(Eveniment e) throws SQLException{
        String command = """
                INSERT INTO EVENIMENTE
                (nume, data, capacitate, tip)
                VALUES (?, ?, ?, ?)
            """;
        try(PreparedStatement ps = conn.prepareStatement(command,Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1,e.getNume());
            ps.setString(2,e.getData());
            ps.setInt(3, e.getCapacitate());
            ps.setString(4,e.getTip().name());

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next())
                    e.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public Optional<Eveniment> findById(Integer id) throws SQLException{
        String command = "SELECT * FROM EVENIMENTE WHERE id=?";
        try(PreparedStatement ps = conn.prepareStatement(command)){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Eveniment e = new Eveniment(rs.getInt("id"), rs.getString("nume"), rs.getString("data"), rs.getInt("capacitate"), TipBilet.valueOf(rs.getString("tip")));
                    return Optional.of(e);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Eveniment> findAll() throws SQLException{
        List<Eveniment> evenimente = new ArrayList<>();
        String command = "SELECT * FROM EVENIMENTE ORDER BY id";
        try(PreparedStatement ps = conn.prepareStatement(command);
            ResultSet rs = ps.executeQuery()
        )
        {
            while(rs.next()){
                Eveniment e = new Eveniment(rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("data"),
                    rs.getInt("capacitate"),
                    TipBilet.valueOf(rs.getString("tip")));
                evenimente.add(e);
            }
        }
        return evenimente;
    }

    @Override
    public void update(Eveniment e) throws SQLException{
        String command = """
                UPDATE EVENIMENTE
                SET nume=?,
                data=?,
                capacitate=?,
                tip=?
                WHERE id=?
            """;
        
        try(PreparedStatement ps = conn.prepareStatement(command)){
            ps.setString(1,e.getNume());
            ps.setString(2,e.getData());
            ps.setInt(3,e.getCapacitate());
            ps.setString(4,e.getTip().name());
            ps.setInt(5,e.getId());

            ps.executeUpdate();
        }
    }
    
    @Override
    public void delete(Integer id) throws SQLException{
        deleteImpl(id);
    }

    public int deleteImpl(int id) throws SQLException{
        String command = "DELETE FROM EVENIMENTE WHERE id=?";
        try(PreparedStatement ps = conn.prepareStatement(command)){
            ps.setInt(1,id);
            return ps.executeUpdate();
        }
    }

    public int count() throws SQLException{
        String command = "SELECT COUNT(*) FROM EVENIMENTE";
        try(PreparedStatement ps = conn.prepareStatement(command);
            ResultSet rs = ps.executeQuery()
        ){
            if(rs.next())
                return rs.getInt(1);
        }
        return 0;
    }
}
