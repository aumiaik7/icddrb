using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace DataTransferApp
{
        [ServiceContract()]
    public interface IDataTransService
    {
            [OperationContract()]
            [WebInvoke(UriTemplate = "CCDRDUpload", Method = "POST",
                RequestFormat = WebMessageFormat.Json
                , ResponseFormat = WebMessageFormat.Json)]
            List<TransData> UploadData(List<TransData> oEmp);
    }
}
