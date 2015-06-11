using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.Data;
using System.Data.SqlClient;
using System.Web;
using System.Diagnostics;


namespace DataTransferApp
{
    class DataTransService : IDataTransService
    {
        public SqlConnection IDisconnection;
        public List<TransData> UploadData(List<TransData> ReceiveTData)
        { 
            List<TransData> backList = new List<TransData>();
            foreach (TransData TData in ReceiveTData)
            {
                TransData backDataFromSVC = new TransData();
                backDataFromSVC.DatabaseNm = TData.DatabaseNm;
                backDataFromSVC.TableNm = TData.TableNm;

                string conn = DbHelper.GetResultSet(TData.DatabaseNm);
                TransData ConFailMsg = new TransData();
                 try
                 {
                  if (IDisconnection != null)
                 {
                    if (IDisconnection.State == System.Data.ConnectionState.Open)
                    {
                        IDisconnection.Close();
                    }
                   // IDisconnection = null;
                 }
                 else
                 {
                    IDisconnection = new SqlConnection();
                 }
                  IDisconnection.ConnectionString = conn;
                  IDisconnection.Open();
                 }
                 catch (System.Exception ex)
                 {
                     ConFailMsg.DatabaseNm = "errorconnection";
                     backList.Add(ConFailMsg);
                     return backList;
                 }

                List<InsertStatement> backDataFromSVCList = new List<InsertStatement>();
                foreach (InsertStatement TInsertStatement in TData.IStatement)
                {
                    InsertStatement FlagBack = new InsertStatement();
                    SqlConnection connection = new SqlConnection(conn);
                  
                    try
                    {
                        if (TData.TableNm == "tblCensus")
                        {
                        }
                        int datacount = 0;
                        int pcount = TInsertStatement.PrimaryClmList.Count();
                        int a = 0;
                        if (pcount > 0)
                        {
                            string sql = "select count(*) from [" + TData.TableNm + "] where ";
                            foreach (PrimaryClm p in TInsertStatement.PrimaryClmList)
                            {
                                sql = sql + "" + p.ClumnMn + " = " + p.ClumnValue + "";
                                a += 1;
                                if (pcount > a)
                                {
                                    sql = sql + " and ";
                                }
                            }
                            SqlCommand commandf = connection.CreateCommand();
                            connection.Open();
                            commandf.CommandText = sql;
                            datacount = (int)commandf.ExecuteScalar();
                            connection.Close();
                        }

                        if (datacount > 0)
                        {
                            if (TData.TableNm == "emp")
                            {
                            }
                            a = 0;
                            //Delete previous row
                            string sql = "Delete from [" + TData.TableNm + "] where ";
                            foreach (PrimaryClm p in TInsertStatement.PrimaryClmList)
                            {
                                sql = sql + "" + p.ClumnMn + " = " + p.ClumnValue + "";
                                a += 1;
                                if (pcount > a)
                                {
                                    sql = sql + " and ";
                                }
                            }
                            SqlCommand commandf = connection.CreateCommand();
                            connection.Open();
                            commandf.CommandText = sql;
                            commandf.ExecuteNonQuery();
                            connection.Close();
                            //Insert new row
                            SqlCommand command = connection.CreateCommand();
                            connection.Open();
                            command.CommandText = TInsertStatement.Statement;
                            command.ExecuteNonQuery();
                            connection.Close();

                            FlagBack.Statement = "1";
                            FlagBack.PrimaryClmList = TInsertStatement.PrimaryClmList;

                        }

                        else
                        {
                            SqlCommand command = connection.CreateCommand();
                            connection.Open();
                            command.CommandText = TInsertStatement.Statement;
                            command.ExecuteNonQuery();
                            connection.Close();

                            FlagBack.Statement = "1";
                            FlagBack.PrimaryClmList = TInsertStatement.PrimaryClmList;
                        }

                    }
                    catch (Exception ex)
                    {
                        FlagBack.Statement = "0";
                        FlagBack.PrimaryClmList = TInsertStatement.PrimaryClmList;                     
                        SqlConnection connectione = new SqlConnection(conn);
                        try
                        {
                            int pcount = TInsertStatement.PrimaryClmList.Count();
                            int a = 0;
                            string pval = "";
                            if (pcount > 0)
                            {
                            foreach (PrimaryClm p in TInsertStatement.PrimaryClmList)
                            {
                                pval = pval + "" + p.ClumnMn + ":" + p.ClumnValue + "";
                                a += 1;
                                if (pcount > a)
                                {
                                    pval = pval + ",";
                                }
                            }
                            }
                            SqlCommand command = connectione.CreateCommand();
                            connectione.Open();
                           // command.CommandText = "INSERT INTO ExceptionTable(TableName,DataID,InsertTime,AssetId,ErrMes,ErrSql) VALUES('" + TData.TableNm + "',null, GETDATE(),'" + TData.AssetId + "','" + ex.Message.Replace("'", "''") + "','" + TInsertStatement.Statement + "') ";
                            command.CommandText = "INSERT INTO ExceptionTable(TableName,DataID,InsertTime,AssetId,ErrMes,ErrSql) VALUES('" + TData.TableNm + "','" + pval.Replace("'", "''") + "', GETDATE(),'" + TData.AssetId + "','" + ex.Message.Replace("'", "''") + "','" + TInsertStatement.Statement.Replace("'", "''") + "') ";

                           //command.Parameters.AddWithValue("@Value", "John's");
                            
                            
                            command.ExecuteNonQuery();
                            new Exception();
                            continue;
                        }
                        catch (Exception ex1)
                        {
                            throw ex1;
                        }
                        finally
                        {
                            connectione.Close();
                        }
                    }

                    finally
                    {
                        connection.Close();
                        backDataFromSVCList.Add(FlagBack);
                       
                    }
                }
                backDataFromSVC.IStatement = backDataFromSVCList;
                backList.Add(backDataFromSVC);
            }
            return backList;

           
        }


        }

       
  }
