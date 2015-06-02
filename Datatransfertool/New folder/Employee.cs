using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SampleClientService
{
    public class Employee
    {
        public string Empno { get; set; }
        public string Ename { get; set; }
        public string Sal { get; set; }
        public string Deptno { get; set; }
        public List<Role> role { get; set; }
    }
}
