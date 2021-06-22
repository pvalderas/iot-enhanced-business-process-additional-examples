using System;
using System.Collections.Generic;

namespace SoilMoistureSensor
{
    public class ContextProperty
    {
            public string device { get; set; }
            public string id { get; set; }
            public string property { get; set; }
            public string feature { get; set; }
            public List<Property> dataProps { get; set; }
    }
}
