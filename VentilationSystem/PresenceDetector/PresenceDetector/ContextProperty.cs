using System;
using System.Collections.Generic;

namespace PresenceDetector
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
