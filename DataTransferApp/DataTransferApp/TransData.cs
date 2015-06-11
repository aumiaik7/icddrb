using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;

namespace DataTransferApp
{
     [DataContract(Namespace = "")]
     public class TransData
    {
        [DataMember(Order = 0)]
         public string DatabaseNm { get; set; }

        [DataMember(Order = 1)]
        public string TableNm { get; set; }

        [DataMember(Order = 2)]
        public string AssetId { get; set; }

        [DataMember(Order = 3)]
        public List<InsertStatement> IStatement { get; set; }
    }
}
