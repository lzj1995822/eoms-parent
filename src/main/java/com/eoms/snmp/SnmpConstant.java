package com.eoms.snmp;

import org.snmp4j.smi.OID;

public final class SnmpConstant {
    public static final int DEFAULT_COMMAND_RESPONDER_PORT = 161;
    public static final int DEFAULT_NOTIFICATION_RECEIVER_PORT = 162;
    public static final int MIN_PDU_LENGTH = 484;
    public static final int MILLISECOND_TO_NANOSECOND = 1000000;
    public static final int HUNDREDTHS_TO_NANOSECOND = 10000000;
    public static final int version1 = 0;
    public static final int version2c = 1;
    public static final int version3 = 3;
    public static final int SNMP_ERROR_TIMEOUT = -1;
    public static final int SNMP_ERROR_LEXICOGRAPHIC_ORDER = -2;
    public static final int SNMP_ERROR_REPORT = -3;
    public static final int SNMP_ERROR_IO = -4;
    public static final int SNMP_ERROR_SUCCESS = 0;
    public static final int SNMP_ERROR_TOO_BIG = 1;
    public static final int SNMP_ERROR_NO_SUCH_NAME = 2;
    public static final int SNMP_ERROR_BAD_VALUE = 3;
    public static final int SNMP_ERROR_READ_ONLY = 4;
    public static final int SNMP_ERROR_GENERAL_ERROR = 5;
    public static final int SNMP_ERROR_NO_ACCESS = 6;
    public static final int SNMP_ERROR_WRONG_TYPE = 7;
    public static final int SNMP_ERROR_WRONG_LENGTH = 8;
    public static final int SNMP_ERROR_WRONG_ENCODING = 9;
    public static final int SNMP_ERROR_WRONG_VALUE = 10;
    public static final int SNMP_ERROR_NO_CREATION = 11;
    public static final int SNMP_ERROR_INCONSISTENT_VALUE = 12;
    public static final int SNMP_ERROR_RESOURCE_UNAVAILABLE = 13;
    public static final int SNMP_ERROR_COMMIT_FAILED = 14;
    public static final int SNMP_ERROR_UNDO_FAILED = 15;
    public static final int SNMP_ERROR_AUTHORIZATION_ERROR = 16;
    public static final int SNMP_ERROR_NOT_WRITEABLE = 17;
    public static final int SNMP_ERROR_INCONSISTENT_NAME = 18;
    public static final int SNMP_MP_OK = 0;
    public static final int SNMP_MP_ERROR = -1400;
    public static final int SNMP_MP_UNSUPPORTED_SECURITY_MODEL = -1402;
    public static final int SNMP_MP_NOT_IN_TIME_WINDOW = -1403;
    public static final int SNMP_MP_DOUBLED_MESSAGE = -1404;
    public static final int SNMP_MP_INVALID_MESSAGE = -1405;
    public static final int SNMP_MP_INVALID_ENGINEID = -1406;
    public static final int SNMP_MP_NOT_INITIALIZED = -1407;
    public static final int SNMP_MP_PARSE_ERROR = -1408;
    public static final int SNMP_MP_UNKNOWN_MSGID = -1409;
    public static final int SNMP_MP_MATCH_ERROR = -1410;
    public static final int SNMP_MP_COMMUNITY_ERROR = -1411;
    public static final int SNMP_MP_WRONG_USER_NAME = -1412;
    public static final int SNMP_MP_BUILD_ERROR = -1413;
    public static final int SNMP_MP_USM_ERROR = -1414;
    public static final int SNMP_MP_UNKNOWN_PDU_HANDLERS = -1415;
    public static final int SNMP_MP_UNAVAILABLE_CONTEXT = -1416;
    public static final int SNMP_MP_UNKNOWN_CONTEXT = -1417;
    public static final int SNMP_MP_REPORT_SENT = -1418;
    public static final int SNMPv1v2c_CSM_OK = 0;
    public static final int SNMPv1v2c_CSM_BAD_COMMUNITY_NAME = 1501;
    public static final int SNMPv1v2c_CSM_BAD_COMMUNITY_USE = 1502;
    public static final int SNMPv3_USM_OK = 0;
    public static final int SNMPv3_USM_ERROR = 1401;
    public static final int SNMPv3_USM_UNSUPPORTED_SECURITY_LEVEL = 1403;
    public static final int SNMPv3_USM_UNKNOWN_SECURITY_NAME = 1404;
    public static final int SNMPv3_USM_ENCRYPTION_ERROR = 1405;
    public static final int SNMPv3_USM_DECRYPTION_ERROR = 1406;
    public static final int SNMPv3_USM_AUTHENTICATION_ERROR = 1407;
    public static final int SNMPv3_USM_AUTHENTICATION_FAILURE = 1408;
    public static final int SNMPv3_USM_PARSE_ERROR = 1409;
    public static final int SNMPv3_USM_UNKNOWN_ENGINEID = 1410;
    public static final int SNMPv3_USM_NOT_IN_TIME_WINDOW = 1411;
    public static final int SNMPv3_USM_UNSUPPORTED_AUTHPROTOCOL = 1412;
    public static final int SNMPv3_USM_UNSUPPORTED_PRIVPROTOCOL = 1413;
    public static final int SNMPv3_USM_ADDRESS_ERROR = 1414;
    public static final int SNMPv3_USM_ENGINE_ID_TOO_LONG = 1415;
    public static final int SNMPv3_USM_SECURITY_NAME_TOO_LONG = 1416;
    public static final int SNMPv3_TSM_OK = 0;
    public static final int SNMPv3_TSM_UNKNOWN_PREFIXES = 1601;
    public static final int SNMPv3_TSM_INVALID_CACHES = 1602;
    public static final int SNMPv3_TSM_INADEQUATE_SECURITY_LEVELS = 1603;
    public static final int SNMP_MD_OK = 0;
    public static final int SNMP_MD_ERROR = 1701;
    public static final int SNMP_MD_UNSUPPORTED_MP_MODEL = 1702;
    public static final int SNMP_MD_UNSUPPORTED_ADDRESS_CLASS = 1703;
    public static final int SNMP_MD_UNSUPPORTED_SNMP_VERSION = 1704;
    public static final OID usmNoAuthProtocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 1, 1});
    public static final OID usmHMACMD5AuthProtocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 1, 2});
    public static final OID usmHMACSHAAuthProtocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 1, 3});
    public static final OID usmNoPrivProtocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 2, 1});
    public static final OID usmDESPrivProtocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 2, 2});
    public static final OID usm3DESEDEPrivProtocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 2, 3});
    public static final OID usmAesCfb128Protocol = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 1, 2, 4});
    public static final OID oosnmpUsmAesCfb192Protocol = new OID(new int[]{1, 3, 6, 1, 4, 1, 4976, 2, 2, 1, 1, 1});
    public static final OID oosnmpUsmAesCfb256Protocol = new OID(new int[]{1, 3, 6, 1, 4, 1, 4976, 2, 2, 1, 1, 2});
    public static final OID oosnmpUsmAesCfb192ProtocolWith3DESKeyExtension = new OID(new int[]{1, 3, 6, 1, 4, 1, 4976, 2, 2, 1, 2, 1});
    public static final OID oosnmpUsmAesCfb256ProtocolWith3DESKeyExtension = new OID(new int[]{1, 3, 6, 1, 4, 1, 4976, 2, 2, 1, 2, 2});
    public static final OID usmStatsUnsupportedSecLevels = new OID(new int[]{1, 3, 6, 1, 6, 3, 15, 1, 1, 1, 0});
    public static final OID usmStatsNotInTimeWindows = new OID(new int[]{1, 3, 6, 1, 6, 3, 15, 1, 1, 2, 0});
    public static final OID usmStatsUnknownUserNames = new OID(new int[]{1, 3, 6, 1, 6, 3, 15, 1, 1, 3, 0});
    public static final OID usmStatsUnknownEngineIDs = new OID(new int[]{1, 3, 6, 1, 6, 3, 15, 1, 1, 4, 0});
    public static final OID usmStatsWrongDigests = new OID(new int[]{1, 3, 6, 1, 6, 3, 15, 1, 1, 5, 0});
    public static final OID usmStatsDecryptionErrors = new OID(new int[]{1, 3, 6, 1, 6, 3, 15, 1, 1, 6, 0});
    public static final OID snmpEngineID = new OID(new int[]{1, 3, 6, 1, 6, 3, 10, 2, 1, 1, 0});
    public static final OID snmpUnknownSecurityModels = new OID(new int[]{1, 3, 6, 1, 6, 3, 11, 2, 1, 1, 0});
    public static final OID snmpInvalidMsgs = new OID(new int[]{1, 3, 6, 1, 6, 3, 11, 2, 1, 2, 0});
    public static final OID snmpUnknownPDUHandlers = new OID(new int[]{1, 3, 6, 1, 6, 3, 11, 2, 1, 3, 0});
    public static final OID snmpInPkts = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 1, 0});
    public static final OID snmpInBadVersions = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 3, 0});
    public static final OID snmpInBadCommunityNames = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 4, 0});
    public static final OID snmpInBadCommunityUses = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 5, 0});
    public static final OID snmpInASNParseErrs = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 6, 0});
    public static final OID snmpSilentDrops = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 31, 0});
    public static final OID snmpProxyDrops = new OID(new int[]{1, 3, 6, 1, 2, 1, 11, 32, 0});
    public static final OID snmpTrapOID = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 4, 1, 0});
    public static final OID snmpTrapEnterprise = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 4, 3, 0});
    public static final OID snmpTraps = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 5});
    public static final OID coldStart = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 5, 1});
    public static final OID warmStart = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 5, 2});
    public static final OID authenticationFailure = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 5, 5});
    public static final OID linkDown = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 5, 3});
    public static final OID linkUp = new OID(new int[]{1, 3, 6, 1, 6, 3, 1, 1, 5, 4});
    public static final String sysDescr = "1.3.6.1.2.1.1.1.0";
    public static final String sysObjectID = "1.3.6.1.2.1.1.2.0";
    public static final String sysUpTime = "1.3.6.1.2.1.1.3.0";
    public static final String sysContact = "1.3.6.1.2.1.1.4.0";
    public static final String sysName = "1.3.6.1.2.1.1.5.0";
    public static final String sysLocation = "1.3.6.1.2.1.1.6.0";
    public static final String sysServices = "1.3.6.1.2.1.1.7.0";

}

