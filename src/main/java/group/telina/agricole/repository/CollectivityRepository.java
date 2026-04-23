package group.telina.agricole.repository;

import group.telina.agricole.entity.Account;
import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.entity.Member;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityRepository {

    private final Connection connection;

    public CollectivityRepository(
            Connection connection
    ){
        this.connection = connection;
    }


    // ==================================
    // EXISTANT
    // ==================================

    public Collectivity save(
            Collectivity c
    ){

        try{

            String sql="""
                INSERT INTO collectivity(
                    id,
                    number,
                    name,
                    address,
                    collectivity_type
                )
                VALUES (?,?,?,?,?)
            """;

            PreparedStatement ps=
                    connection.prepareStatement(sql);

            ps.setString(1,c.getId());
            ps.setInt(2,c.getNumber());
            ps.setString(3,c.getName());
            ps.setString(4,c.getAddress());
            ps.setString(
                    5,
                    c.getCollectivityType()
            );

            ps.executeUpdate();

            return c;

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }



    public List<Collectivity> findAll(){

        List<Collectivity> list=
                new ArrayList<>();

        try{

            String sql="""
              SELECT * FROM collectivity
            """;

            PreparedStatement ps=
                    connection.prepareStatement(sql);

            ResultSet rs=
                    ps.executeQuery();

            while(rs.next()){

                Collectivity c=
                        new Collectivity();

                c.setId(
                        String.valueOf(Integer.valueOf(rs.getString("id")))
                );

                c.setNumber(
                        rs.getInt("number")
                );

                c.setName(
                        rs.getString("name")
                );

                c.setAddress(
                        rs.getString("address")
                );

                c.setCollectivityType(
                        rs.getString(
                                "collectivity_type"
                        )
                );

                list.add(c);

            }

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

        return list;
    }



    // ==================================
    // GET /collectivities/{id}
    // ==================================
    public Collectivity findById(
            String id
    ){

        try{

            String sql="""
                SELECT *
                FROM collectivity
                WHERE id=?
            """;

            PreparedStatement ps=
                    connection.prepareStatement(sql);

            ps.setString(1,id);

            ResultSet rs=
                    ps.executeQuery();

            if(!rs.next()){
                return null;
            }

            Collectivity c=
                    new Collectivity();

            c.setId(
                    String.valueOf(Integer.valueOf(rs.getString("id")))
            );

            c.setNumber(
                    rs.getInt("number")
            );

            c.setName(
                    rs.getString("name")
            );

            c.setAddress(
                    rs.getString("address")
            );

            c.setCollectivityType(
                    rs.getString(
                            "collectivity_type"
                    )
            );



            // charger members
            String memberSql="""
               SELECT *
               FROM member
               WHERE collectivity_id=?
            """;

            PreparedStatement ms=
                    connection.prepareStatement(
                            memberSql
                    );

            ms.setString(1,id);

            ResultSet mr=
                    ms.executeQuery();

            List<Member> members=
                    new ArrayList<>();

            while(mr.next()){

                Member m=
                        new Member();

                m.setId(
                        String.valueOf(Integer.valueOf(mr.getString("id")))
                );

                m.setFirstName(
                        mr.getString("first_name")
                );

                m.setLastName(
                        mr.getString("last_name")
                );

                m.setOccupation(
                        mr.getString("occupation")
                );

                members.add(m);
            }

            c.setMembers(members);

            return c;

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }




    // ==================================
    // GET financial accounts
    // ==================================
    public List<Account> findFinancialAccounts(
            String collectivityId,
            String at
    ){

        List<Account> accounts=
                new ArrayList<>();

        try{

            String sql="""
              SELECT
                a.id,
                a.account_type,
                a.balance +
                COALESCE(
                  SUM(p.amount),
                  0
                ) as balance

              FROM account a

              LEFT JOIN payment p
              ON a.id=p.account_id
              AND p.payment_date<=?

              WHERE a.collectivity_id=?

              GROUP BY
                a.id,
                a.account_type,
                a.balance
            """;

            PreparedStatement ps=
                    connection.prepareStatement(
                            sql
                    );

            ps.setDate(
                    1,
                    Date.valueOf(at)
            );

            ps.setString(
                    2,
                    collectivityId
            );

            ResultSet rs=
                    ps.executeQuery();

            while(rs.next()){

                Account a=
                        new Account();

                a.setId(
                        Integer.valueOf(rs.getString("id"))
                );

                a.setAccountType(
                        rs.getString(
                                "account_type"
                        )
                );

                a.setBalance(
                        rs.getDouble(
                                "balance"
                        )
                );

                accounts.add(a);
            }

        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

        return accounts;

    }

}