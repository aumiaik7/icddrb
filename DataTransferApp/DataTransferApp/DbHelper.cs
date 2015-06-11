using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;

namespace DataTransferApp
{
    public static class DbHelper
    {
        private static string ConnectionString
        {
            get
            {
                return System.Configuration.ConfigurationManager.ConnectionStrings["cn"].ConnectionString;
            }
        }
        //"Data Source= 172.16.10.28\\ANGSUMAN;Initial Catalog=Northwind;User ID=sa;Password=12345678";
        public static string GetResultSet(string DataId)
        {
            string ConStr = "";
            string query = "SELECT * FROM tblDTConfig where ID = '" + DataId + "'";
            SqlConnection con = new SqlConnection(ConnectionString);
            con.Open();
             SqlDataAdapter da = new SqlDataAdapter(query, con);
            da.SelectCommand.CommandType = CommandType.Text;
            DataSet ds = new DataSet();
            da.Fill(ds);
            con.Close();
            if (ds.Tables[0].Rows.Count > 0)
            {
                foreach (DataRow row in ds.Tables[0].Rows)
                {
                    ConStr = "Data Source= " + row["ServerName"].ToString() + ";Initial Catalog=" + row["DatabaseName"].ToString() + ";User ID=" + row["UserName"].ToString() + ";Password=" + row["Password"].ToString() + "";
                   // ConStr = "Data Source= Data Source=CCD-RZAMAN\\SQLEXPRESS;Initial Catalog=" + row["DatabaseName"].ToString() + ";User ID=" + row["UserName"].ToString() + ";Password=" + row["Password"].ToString() + "";
  
                }
            }

            return ConStr;
        }
        public static void SqlExecute(string sql)
        {
            using (SqlCommand cmd = new SqlCommand(sql, new SqlConnection(ConnectionString)))
            {
                cmd.Connection.Open();
                cmd.ExecuteNonQuery();
                cmd.Connection.Close();
            }
        }

    }
}
